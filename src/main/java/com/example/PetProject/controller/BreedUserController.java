package com.example.PetProject.controller;

import com.example.PetProject.domain.*;
import com.example.PetProject.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping
@Controller
public class BreedUserController {
    private final BreedService breedService;
    private final CommunityService communityService;


    @Autowired
    public BreedUserController(BreedService breedService, CommunityService communityService) {
        this.breedService = breedService;
        this.communityService = communityService;
    }


    @GetMapping("/breed")
    public String breedpage(Model model) {
        List<Breed> breed_list = breedService.getReportedAndDeletedBreeds();
        model.addAttribute("breed_list", breed_list);
        return "breed";
    }

    @ResponseBody
    @RequestMapping(value = {"/delete_breed"}, method = {RequestMethod.POST})
    public int delete_breed(@RequestBody List<Integer> breedId) {
        return breedService.deleteBreed(breedId);
    }
    @ResponseBody
    @RequestMapping(value = {"/delete_comm"}, method = {RequestMethod.POST})
    public int delete_comm(@RequestBody List<Integer> commId) {
        return communityService.deletecomm(commId);
    }

    //등록
    @RequestMapping(value = {"/insert_breed"}, method = {RequestMethod.GET})
    public String insertPage_breed () {
        return "breed_insert";
    }

    @ResponseBody
    @RequestMapping(value = {"/insert_breed"}, method = {RequestMethod.POST})
    public int insert_breed (@RequestBody Breed breed_insert){
        return breedService.insertBreed(breed_insert);
    }

    //수정(게시글)
    @RequestMapping(value = {"/breed_view"}, method = {RequestMethod.GET})
    public String view_breed (@RequestParam(value = "breedId", required = false) Integer breedId,
                              Model model){

        Optional<Breed> breed_viewList = breedService.viewBreed(breedId);
        model.addAttribute("breed_viewList", breed_viewList);
        return "breed_view";
    }

    //db data update
    @ResponseBody
    @RequestMapping(value = {"/update_breed"}, method = {RequestMethod.POST})
    public int update_breed(@RequestBody Breed breed_update) {
        return breedService.updatebreed(breed_update);
    }


    //수정(커뮤니티)
    @RequestMapping(value = {"/comm_view"}, method = {RequestMethod.GET})
    public String view_comm (@RequestParam(value = "commId", required = false) Integer commId,
                              Model model){

        Optional<Community> comm_viewList = communityService.viewcomm(commId);
        model.addAttribute("comm_viewList", comm_viewList);
        return "comm_view";
    }

    //db data update
    @ResponseBody
    @RequestMapping(value = {"/update_comm"}, method = {RequestMethod.POST})
    public int update_comm(@RequestBody Community comm_update) {
        return communityService.updatecomm(comm_update);
    }


    @GetMapping("/breed_user")
    public String getBreedUserPage(Model model) {
        List<Breed> breedList = breedService.getAllBreeds()
                .stream()
                .filter(breed -> breed.getState() == null && "강아지".equals(breed.getType()))
                .collect(Collectors.toList()); //null값 허용, 수정 허용
        model.addAttribute("breed_list", breedList);
        System.out.println(breedList);
        return "breed_user";
    }
    @GetMapping("/breed_user_cat")
    public String getBreedUserCatPage(Model model) {
        List<Breed> breedList = breedService.getAllBreeds()
                .stream()
                .filter(breed -> breed.getState() == null && "고양이".equals(breed.getType()))
                .collect(Collectors.toList()); //null값 허용, 수정 허용
        model.addAttribute("breed_list", breedList);
        return "breed_user_cat";
    }
    @GetMapping("/breed_user_ex")
    public String getBreedUserExPage(Model model) {
        List<Breed> breedList = breedService.getAllBreeds()
                .stream()
                .filter(breed -> breed.getState() == null && "그외".equals(breed.getType()))
                .collect(Collectors.toList()); //null값 허용, 수정 허용
        model.addAttribute("breed_list", breedList);
        return "breed_user_ex";
    }
    /*@GetMapping("/breed_write")//글쓰기 수정, 등록
    public String breedwriteuser(Model model){

    }*/
    @GetMapping("/comm_user")
    public String comm_page(Model model) {
        List<Community> commu_list = communityService.getList();
        model.addAttribute("commu_list", commu_list);
        return "comm_user";
    }

    @GetMapping("/comm")
    public String comm_viewpage(Model model) {
        List<Community> commu_list = communityService.getList();
        model.addAttribute("commu_list", commu_list);
        return "/comm";
    }

    @ResponseBody
    @GetMapping("/search")//Breed의 검색
    public List<Breed> searchBreeds(@RequestParam String query) {
        List<Breed> allBreeds = breedService.findAllBreeds()
                .stream()
                .filter(breed -> breed.getState() == null && "강아지".equals(breed.getType()))
                .collect(Collectors.toList());
        return allBreeds.stream()
                .filter(breed -> breed.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    @ResponseBody
    @GetMapping("/search_cat")//Breed의 검색
    public List<Breed> searchCatBreeds(@RequestParam String query) {
        List<Breed> allBreeds = breedService.findAllBreeds()
                .stream()
                .filter(breed -> breed.getState() == null && "고양이".equals(breed.getType()))
                .collect(Collectors.toList());
        return allBreeds.stream()
                .filter(breed -> breed.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    @ResponseBody
    @GetMapping("/search_ex")//Breed의 검색
    public List<Breed> searchExBreeds(@RequestParam String query) {
        List<Breed> allBreeds = breedService.findAllBreeds()
                .stream()
                .filter(breed -> breed.getState() == null && "그외".equals(breed.getType()))
                .collect(Collectors.toList());
        return allBreeds.stream()
                .filter(breed -> breed.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/breed_user_write")
    public String breedUserWrite(@RequestParam(value = "breedIds", required = false) Integer breedIds) {
        return "breed_user_write";
    }

    /*@RequestMapping(value = {"/write_breed"}, method = {RequestMethod.GET})
    public String insertPage_faq() {
        return "faq_insert";
    }

    //등록버튼 누르면 db에 insert
    @ResponseBody
    @RequestMapping(value = {"/write_breed"}, method = {RequestMethod.POST})
    public int write_breed(@RequestBody Breed faq_insertOp){
        return faqService.insertFAQs(faq_insertOp);*/

    @PostMapping("/write")
    @ResponseBody
    public String write_breed(HttpServletRequest request,  @RequestParam("boardFile")MultipartFile multipartFile) throws IOException {
        String title = request.getParameter("title");
        System.out.println(title);
        System.out.println(multipartFile.getOriginalFilename().toString());

        /*String fileName = multipartFile.getOriginalFilename();
        String resourcePath = new File("src/main/resources/img").getAbsolutePath();
        String imagepath = resourcePath + "/" + fileName;
        File dest = new File(imagepath);
        multipartFile.transferTo(dest);


        Breed breedData = new Breed();
        breedData.setTitle(title);
        breedData.setBreed(breed);
        breedData.setContent(content);
        breedData.setImagepath(imagepath);;

        breedService.saveBreed(breedData);



        System.out.println(title);
        System.out.println(breed);
        System.out.println(content);
        System.out.println(member_id);
        System.out.println(multipartFile.getOriginalFilename().toString());

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            breed.setImagepath(fileName);
            System.out.println(breed.getImagepath());

            Breed breed1 = breedService.save(breed);

            String uploadDir = "boardFile" + breed1.getBreed_id(); //디비 가기 전 가공 과정
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }else {
            breed.setImagepath(null);
            breedService.save(breed);
        }*/
        return "ok";

    }
}




