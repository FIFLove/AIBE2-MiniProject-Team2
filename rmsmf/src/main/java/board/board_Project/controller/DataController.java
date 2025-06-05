package board.board_Project.controller;


import board.board_Project.domain.Data;
import board.board_Project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 게시글 목록을 보여줄 Controller 이다.
@Controller
public class DataController {
    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String showBoard(Model model) {
        List<Data> posts = dataService.findAll();
        model.addAttribute("posts", posts);
        return "board"; // templates/board.html 을 찾음
    }

    @GetMapping("/new")
    public String showForm() {
        return "new"; // new.html로 이동
    }

    @PostMapping("/new")
    public String savePost(@RequestParam String title,
                           @RequestParam String name,
                           @RequestParam String content,
                           @RequestParam String password) {
        Data data = new Data();
        data.setTitle(title);
        data.setName(name);
        data.setContent(content);
        data.setPassword(password);
        dataService.save(data);
        return "redirect:/"; // 저장 후 목록으로 이동
    }


    //    추가
    // 삭제 버튼 클릭 시
    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);  // 글의 ID를 모델에 추가
        return "confirmDelete";  // 비밀번호를 확인하는 페이지로 이동
    }

    // 삭제 요청 처리
    @PostMapping("/posts/{id}/delete")
    public String confirmDelete(@PathVariable Long id, @RequestParam String password) {
        Data data = dataService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        if (data.getPassword().equals(password)) {  // 비밀번호 확인
            dataService.delete(id);  // 비밀번호 맞으면 삭제
        }
        return "redirect:/";  // 게시판으로 돌아가기
    }

    // 수정 버튼 클릭 시
    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        Data data = dataService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
        model.addAttribute("post", data);  // 수정할 게시글 데이터를 모델에 추가
        return "editPost";  // 수정 페이지로 이동
    }

    // 수정 요청 처리
    @PostMapping("/posts/{id}/edit")
    public String confirmEdit(@PathVariable Long id, @RequestParam String password, @ModelAttribute Data data) {
        Data originalPost = dataService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        if (originalPost.getPassword().equals(password)) {  // 비밀번호 확인
            originalPost.setTitle(data.getTitle());
            originalPost.setContent(data.getContent());
            dataService.save(originalPost);  // 수정된 게시글 저장
        }
        return "redirect:/";
    }
}
