package wad.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.Category;
import wad.domain.Image;
import wad.domain.NewClass;
import wad.repository.ImageRepository;
import wad.repository.NewRepository;
import wad.repository.CategoryRepository;

@Controller
public class DefaultController {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CountService countService;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "created");
        model.addAttribute("news", newRepository.findAll(pageable));
        model.addAttribute("cats", categoryRepository.findAll());
        return "news";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Model model) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "read");
        model.addAttribute("news", newRepository.findAll(pageable));
        model.addAttribute("cats", categoryRepository.findAll());
        return "news";
    }

    @RequestMapping(value = "/published", method = RequestMethod.GET)
    public String published(Model model) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "created");
        model.addAttribute("news", newRepository.findAll(pageable));
        model.addAttribute("cats", categoryRepository.findAll());
        return "news";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String single(Model model, @PathVariable Long id) {
        countService.incrementAndCount(id);
        model.addAttribute("news", newRepository.findById(id).get());
        model.addAttribute("cats", categoryRepository.findAll());
        return "newhtml";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("cats", categoryRepository.findAll());
        return "add";
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String byCategory(Model model, @PathVariable Long id) {
        model.addAttribute("news", categoryRepository.findById(id).get().getNews());
        model.addAttribute("name", categoryRepository.findById(id).get());
        model.addAttribute("cats", categoryRepository.findAll());
        return "category";
    }

    @RequestMapping(value = "/delete")
    public String delete() {
        categoryRepository.deleteAll();
        newRepository.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/add")
    public String post(Model model, @RequestParam String name, @RequestParam String lead, @RequestParam String text,
             @RequestParam(required = false) String category
    //            ,@RequestParam("file") MultipartFile file     //tämä sen takia että heroku ei hyväksynyt kuvien talletusta
    ) throws IOException {
        model.addAttribute("cats", categoryRepository.findAll());
        if (!(name.trim().isEmpty()) && !(category == null) && !(lead.trim().isEmpty()) && !(text.trim().isEmpty())
                && name.length()<30 && text.length()<250 && lead.length()<250) {
            Date d = new Date();

            NewClass n = new NewClass();
            n.setName(name);
            n.setLead(lead);
            n.setText(text);
            n.setCreated(d);

//Herokulle ei kelvannut kuvan talletus :(
//            Image i = new Image();
//            i.setContent(file.getBytes());
//            Long idi = imageRepository.save(i).getId();
//
//            n.setI(i);
            NewClass nc = newRepository.save(n);
            newRepository.flush();
            //Kategorian talletus ei ollutkaan niin helppoa kuin luulin
            String[] s = category.split(",");

            Long[] l = new Long[s.length];
            for (int j = 0; j < s.length; j++) {
                l[j] = Long.valueOf(s[j]);
                System.out.println(l[j]);
            }
            System.out.println(l.length);
            for (int k = 0; k < s.length; k++) {
                categoryRepository.getOne(l[k]).addNew(nc);
                newRepository.flush();
                newRepository.getOne(nc.getId()).addCategory(categoryRepository.getOne(l[k]));

            }

        }

        return "redirect:/";
    }

    @PostMapping("/addCategory")

    public String addCategory(@RequestParam String category) {
        if (!(category.trim().isEmpty())) {
            Category c = new Category();
            c.setName(category);
            categoryRepository.save(c);
        }

        return "redirect:/";
    }

    //Tämä metodi ei ole käytettävissä koska kuvaa ei voinut tallentaa herokussa haluamallani tavalla
    @GetMapping(path = "/news/{id}/content", produces = "image/jpg")
    @ResponseBody
    @Transactional
    public byte[] get(@PathVariable Long id) {

        return newRepository.getOne(id).getI().getContent();
    }

}
