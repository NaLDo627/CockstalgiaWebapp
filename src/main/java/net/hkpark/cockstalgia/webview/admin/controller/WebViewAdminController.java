package net.hkpark.cockstalgia.webview.admin.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hkpark.cockstalgia.chatbot.kakaoopenbuilder.member.service.MemberService;
import net.hkpark.cockstalgia.core.annotation.PrintArguments;
import net.hkpark.cockstalgia.core.dto.ResultDto;
import net.hkpark.cockstalgia.core.repository.CocktailRepository;
import net.hkpark.cockstalgia.core.service.CocktailDataService;
import net.hkpark.cockstalgia.core.util.FileUtil;
import net.hkpark.cockstalgia.webview.admin.service.WebViewAdminService;
import net.hkpark.cockstalgia.webview.core.service.WebViewCoreService;
import net.hkpark.kakao.openbuilder.dto.request.SkillRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class WebViewAdminController {
    private final WebViewAdminService webViewAdminService;
    private final CocktailRepository cocktailRepository;

    @GetMapping(value = {"", "/"})
    public String index(Model model) {
        model.addAttribute("cocktailCount", cocktailRepository.count());
        return "admin/index";
    }

    @GetMapping(value = {"/cocktail"})
    public String cocktail(Model model) {
        return "admin/cocktail";
    }

    @GetMapping(value = {"/liquor-bases"})
    @ResponseBody
    public List<String> liquorBases() {
        return webViewAdminService.getLiquorBases();
    }

    @PostMapping(value = {"/upload-cocktail"})
    @ResponseBody
    public ResultDto uploadImage(@RequestParam("cocktail-image") MultipartFile cocktailImageFile) {
        return webViewAdminService.saveMultipartFile(cocktailImageFile);
    }
}
