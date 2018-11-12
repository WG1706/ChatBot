package com.example;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.vng.zalo.sdk.APIException;
import com.vng.zalo.sdk.oa.ZaloOaClient;
import com.vng.zalo.sdk.oa.ZaloOaInfo;

@SpringBootApplication
@Controller
public class ChatBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBotApplication.class, args);
	}

	Long oaid = 2082513010562972030L;
	String secretKey = "kwY61ezzsYc68H2TdCLE";

	@RequestMapping(path = "/zalobot")
	public String botMethod(@RequestParam Map<String, String> data) throws APIException {
		System.out.println(data.get("fromuid"));
		System.out.println(data.get("message"));
		Long fromuid = Long.parseLong(data.get("fromuid"));
		String mes = data.get("message");
		ZaloOaInfo oaInfo = new ZaloOaInfo(oaid, secretKey);
		ZaloOaClient oaClient = new ZaloOaClient(oaInfo);
		JsonObject userProfile = oaClient.getProfile(fromuid);
		userProfile = userProfile.getAsJsonObject("data");
		JsonObject ret = oaClient.sendTextMessage(fromuid, "Hello " + userProfile.get("displayName"));
		return "zalobot";
	}
	@RequestMapping("/home")
	public String homePage(Model model) {
		model.addAttribute("appName", "Test");
		return "home";
	}
}
