package com.sprintboot.app.helloworld;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import netscape.javascript.JSObject;

@RestController
public class HelloWorldController {
	
	// GET HTTP Method
		// http://localhost:8080/hello-world
		@GetMapping("/hello-world")
		public Map<String, String> helloWorld() {
			Map<String, String> map = new HashMap<>();
			map.put("message", "Hello World!");
			return map;
		}
		
		@GetMapping("/hello-world-JSON")
		public String helloWorldJSON() {
			JSONObject json = new JSONObject();
			json.put("message", "Hello World!");
			return json.toString();
		}
		
		@GetMapping("/hello-world1")
		public String helloWorld1() {
			return "Hello World!";
		}

}
