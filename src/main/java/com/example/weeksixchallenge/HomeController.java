package com.example.weeksixchallenge;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CloudinaryConfig cloudc;


    @RequestMapping("/")
    public String listTeams(Model model){
        model.addAttribute("teams", teamRepository.findAll());
        return "index";
    }

    @GetMapping("/addteam")
    public String addTeam(Model model){
        model.addAttribute("team", new Team());
        return "teamform";
    }

    @PostMapping("/addteam")
    public String processTeam(@Valid Team team, BindingResult result, Model model){
        if(result.hasErrors()){
            return "teamform";
        }
        teamRepository.save(team);
        return "redirect:/";
    }

    @GetMapping("/addplayer")
    public String addPlayer(Model model){
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamRepository.findAll());
        return "playerform";
    }

    @PostMapping("/addplayer")
    public String processPlayer(@Valid Player player, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
       if(result.hasErrors()){
           return "playerform";
       }
        else if (file.isEmpty() && (player.getPic() == null || player.getPic().isEmpty())) {
            player.setPic("https://res.cloudinary.com/nk4363/image/upload/v1628864009/placeholder_znzhxi.png");
        } else if (!file.isEmpty()) {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                player.setPic(uploadResult.get("url").toString());
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/addplayer";
            }
        }
        playerRepository.save(player);
        return "redirect:/";
    }

    @RequestMapping("/teamdetails/{id}")
    public String teamDetails(@PathVariable("id") long id, Model model){
        model.addAttribute("team", teamRepository.findById(id).get());
        model.addAttribute("players", playerRepository.findAll());
        return "teamdetails";
    }

    @RequestMapping("/playerdetails/{id}")
    public String playerDetails(@PathVariable("id") long id, Model model){
        model.addAttribute("player", playerRepository.findById(id).get());
        model.addAttribute("teams", teamRepository.findAll());
        return "playerdetails";
    }

    @RequestMapping("/updateteam/{id}")
    public String updateTeam(@PathVariable("id") long id, Model model){
        model.addAttribute("team", teamRepository.findById(id).get());
        return "teamform";
    }

    @RequestMapping("/deleteteam/{id}")
    public String deleteTeam(@PathVariable("id") long id){
        teamRepository.deleteById(id);
        return "redirect:/";
    }






    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }


}