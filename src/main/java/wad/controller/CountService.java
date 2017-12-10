/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.repository.NewRepository;

@Service
public class CountService extends HttpServlet{

    @Autowired
    private NewRepository newRepository;
    
    
    public void incrementAndCount(Long id) {       
       newRepository.getOne(id).setRead(newRepository.getOne(id).getRead()+1);
       newRepository.flush();
       

       
    }
}