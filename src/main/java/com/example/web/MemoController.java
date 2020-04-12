package com.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Memo;
import com.example.service.LoginUserDetails;
import com.example.service.MemoService;

@Controller
@RequestMapping("memos")
public class MemoController {
	@Autowired
	MemoService memoService;

	@ModelAttribute
	MemoForm setUpForm() {
		return new MemoForm();
	}

	@GetMapping
	String list(Model model) {
		List<Memo> memos = memoService.findAll();
		model.addAttribute("memos", memos);
		return "memos/list";
	}

	@PostMapping(path = "create")
	String create(@Validated MemoForm form, BindingResult result, Model model,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return list(model);
		}
		Memo memo = new Memo();
		BeanUtils.copyProperties(form, memo);
		memoService.create(memo, userDetails.getUser());
		return "redirect:/memos";
	}

	@GetMapping(path = "edit", params = "form")
	String editForm(@RequestParam Integer id, MemoForm form) {
		Memo memo = memoService.findOne(id);
		BeanUtils.copyProperties(memo, form);
		return "memos/edit";
	}

	@PostMapping(path = "edit")
	String edit(@RequestParam Integer id, @Validated MemoForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return editForm(id, form);
		}
		Memo memo = new Memo();
		BeanUtils.copyProperties(form, memo);
		memo.setId(id);
		memoService.update(memo, userDetails.getUser());
		return "redirect:/memos";
	}

	@GetMapping(path = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/memos";
	}

	@PostMapping(path = "delete")
	String delete(@RequestParam Integer id) {
		memoService.delete(id);
		return "redirect:/memos";
	}
}