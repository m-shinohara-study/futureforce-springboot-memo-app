package com.lesson.memo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="姓を入力してください")
	@Size(max=40)
	@Column(nullable = false)
	private String lastName;
	
	@NotBlank(message="名を入力してください")
	@Size(max=40)
	@Column(nullable = false)
	private String firstName;
	
	@Email(message="メッセージの形式が正しくありません")
	@NotBlank(message="メールアドレスを入力してください")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotBlank(message="パスワードを入力してください")
	@Size(min=10, max=100, message="パスワードは10文字以上で入力してください")
	@Column(nullable = false)
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;
}
