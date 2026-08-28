package com.example.library;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public Member add(@RequestBody Member member) {
        return service.addMember(member);
    }

    @GetMapping
    public List<Member> getAll() {
        return service.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member get(@PathVariable Long id) {
        return service.getMember(id);
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable Long id, @RequestBody Member member) {
        return service.updateMember(id, member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteMember(id);
    }
}