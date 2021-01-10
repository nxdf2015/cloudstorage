package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.InvalidData;
import jdk.jfr.Experimental;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ErrorController {



  @ExceptionHandler(InvalidData.class)
    public ModelAndView renderInvalidDataError(HttpServletRequest request, Exception e){
      ModelAndView modelAndView = new ModelAndView();
      System.out.println(e.getClass());
      modelAndView.addObject("success",false);
      modelAndView.addObject("message",true);
      modelAndView.addObject("error", e.getMessage());
      modelAndView.setViewName("result");
      return modelAndView;

  }

  @ExceptionHandler(Exception.class)
  public ModelAndView renderIOError(HttpServletRequest request, Exception e){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("success",false);
    modelAndView.addObject("message",false);
    modelAndView.setViewName("result");
    return modelAndView;
  }
}
