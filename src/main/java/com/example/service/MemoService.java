package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Memo;
import com.example.domain.User;
import com.example.repository.MemoRepository;

@Service
@Transactional
public class MemoService {
	@Autowired
	MemoRepository memoRepository;

	public List<Memo> findAll() {
		return memoRepository.findAllOrderByName();
	}

	public Page<Memo> findAll(Pageable pageable) {
		return memoRepository.findAllOrderByName(pageable);
	}

	public Memo findOne(Integer id) {
		return memoRepository.findById(id).get();
	}

	public Memo create(Memo memo, User user) {
		memo.setUser(user);
		return memoRepository.save(memo);
	}

	public Memo update(Memo memo, User user) {
		memo.setUser(user);
		return memoRepository.save(memo);
	}

	public void delete(Integer id) {
		memoRepository.deleteById(id);
	}
}