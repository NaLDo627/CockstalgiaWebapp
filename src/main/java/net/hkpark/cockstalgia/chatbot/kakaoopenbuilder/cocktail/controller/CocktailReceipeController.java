package net.hkpark.cockstalgia.chatbot.kakaoopenbuilder.cocktail.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hkpark.cockstalgia.chatbot.kakaoopenbuilder.cocktail.service.CocktailBasicService;
import net.hkpark.cockstalgia.core.annotation.PrintArguments;
import net.hkpark.kakao.openbuilder.dto.request.SkillRequestDto;
import net.hkpark.kakao.openbuilder.dto.response.SkillResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatbot/cocktail")
public class CocktailReceipeController {
    private final CocktailBasicService cocktailBasicService;

    @PrintArguments
    @RequestMapping(value = "/receipe/v1", method = RequestMethod.POST)
    public ResponseEntity<?> base(@RequestBody SkillRequestDto skillResultDto) {
        SkillResponseDto responseDto = cocktailBasicService.getReceipe(skillResultDto);
        return ResponseEntity.ok(responseDto);
    }
}
