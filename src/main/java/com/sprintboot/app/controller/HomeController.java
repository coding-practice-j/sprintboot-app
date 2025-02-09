package com.sprintboot.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/home")
public class HomeController {
	
	@PostMapping("/calculate")
    public String calculateSum(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
        // Calculate the sum of two numbers
        int sum = num1 + num2;
		JSONObject jsonResponse = new JSONObject(); 
		jsonResponse.put("sum", sum);
		/*
		 * Map<String, Integer> map = new HashMap<>(); map.put("sum", sum);
		 */
		//return map;
        return jsonResponse.toString();
       // return "{\"sum\": " + sum + "}";
    }
    
    @PostMapping("/calculateNew")
    public Map<String, Integer> calculateAddition(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
        // Calculate the sum of two numbers
        int sum = num1 + num2;
		
		/*JSONObject jsonResponse = new JSONObject(); 
		jsonResponse.put("sum", sum);*/		 
		Map<String, Integer> map = new HashMap<>();
		map.put("sum", sum);       
		return map;
        //return jsonResponse.toString();
       // return "{\"sum\": " + sum + "}";
    }
    
    @PostMapping("/javaScript")
    public Object javaScriptEngine(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
    	// Create a GraalVM context for JavaScript
    	try (Context context = Context.create("js")) {
    		
    		// Bind the Java variable into the JavaScript execution context
            context.getBindings("js").putMember("num1", num1);
            context.getBindings("js").putMember( "num2", num2);
    		
    		// JavaScript code to add two numbers
            String jsCode = "var a = num1; var b = num2; var sum = a + b; sum;";
            // Execute JavaScript code
            Value result = context.eval("js", jsCode);
            System.out.println("result: " + result);
            return result.asInt();
        }
    }

}
