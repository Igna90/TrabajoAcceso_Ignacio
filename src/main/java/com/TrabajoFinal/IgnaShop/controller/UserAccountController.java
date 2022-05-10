package com.TrabajoFinal.IgnaShop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.TrabajoFinal.IgnaShop.repository.ConfirmationTokenRepository;
import com.TrabajoFinal.IgnaShop.repository.UserRepository;
import com.TrabajoFinal.IgnaShop.service.ArticleService;
import com.TrabajoFinal.IgnaShop.service.CategoryService;
import com.TrabajoFinal.IgnaShop.service.EmailSenderService;
import com.TrabajoFinal.IgnaShop.service.UserService;
import com.TrabajoFinal.IgnaShop.model.ConfirmationToken;
import com.TrabajoFinal.IgnaShop.Constant.ViewConstant;
import com.TrabajoFinal.IgnaShop.entity.ArticleEntity;
import com.TrabajoFinal.IgnaShop.entity.CategoryEntity;
import com.TrabajoFinal.IgnaShop.entity.UsersEntity;

@Controller
@RequestMapping("/user")
public class UserAccountController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private UserService userService;

	@Autowired
	public ArticleService articleService;

	@Autowired
	public CategoryService categoryService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView displayRegistration(ModelAndView modelAndView, UsersEntity user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(ModelAndView modelAndView, UsersEntity user) {

		UsersEntity existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
		if (existingUser != null) {
			modelAndView.addObject("message", "Este correo ya esta registrado.");
			modelAndView.setViewName("errorRegistration");
		} else {
			Date fecha = new Date();
			user.setRegisterDate(fecha);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setPasswordConfirm(passwordEncoder.encode(user.getPasswordConfirm()));
			userRepository.save(user);

			ConfirmationToken confirmationToken = new ConfirmationToken(user);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Complete el registro!!");
			mailMessage.setFrom("ignatiusceferon@gmail.com");
			mailMessage.setText("Por favor pulse el siguiente enlace para verificar su correo: "
					+ "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			modelAndView.addObject("email", user.getEmail());

			modelAndView.setViewName("successfulRegisteration");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			UsersEntity user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
			user.setEnabled(true);
			userRepository.save(user);
			modelAndView.setViewName("accountVerified");
		} else {
			modelAndView.addObject("message", "Este link es invalido o esta corrompido");
			modelAndView.setViewName("errorRegistration");
		}

		return modelAndView;
	}

	@GetMapping("/editUser")
	public String userUpdateForm(HttpSession session, Authentication auth, Model model) throws Exception {
		String email = auth.getName();
		UsersEntity user = userService.findUserByEmail(email);
		session.setAttribute("user", user);
		model.addAttribute("user", user);
		model.addAttribute("editMode", true);

		return ViewConstant.EDITUSER;
	}

	@PostMapping("/editUser/edit")
	public String editUser(@Valid @ModelAttribute("user") UsersEntity userEntity, Model model) throws Exception {

		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setPasswordConfirm(passwordEncoder.encode(userEntity.getPasswordConfirm()));

		userService.editUser(userService.transform(userEntity));
		return "redirect:/";
	}

	@GetMapping("/articles")
	public String listAllArticle(Model model) {

		model.addAttribute("article", new ArticleEntity());
		model.addAttribute("articles", articleService.listAllArticles());
		return ViewConstant.ARTICLE;
	}

	@GetMapping("/Myarticles")
	public String listAllArticle(Model model, Authentication auth) {
		model.addAttribute("article", new ArticleEntity());
		model.addAttribute("articles", articleService.listArticlesByUser(userService.findUserByEmail(auth.getName())));
		return ViewConstant.MYARTICLE;
	}

	@GetMapping("/createArticle")
	public ModelAndView createArticleForm() {
		ModelAndView mav = new ModelAndView(ViewConstant.CREATEARTICLE);
		mav.addObject("article", new ArticleEntity());
		mav.addObject("category", new CategoryEntity());
		mav.addObject("categories", categoryService.listAllCategory());

		return mav;
	}

	@PostMapping("/createArticleSuccess")
	public String createArticle(@Valid @ModelAttribute ArticleEntity article,
			@RequestParam(name = "imgs", required = false) MultipartFile imagen, Authentication auth) {

		if (!imagen.isEmpty()) {
			Path imageDirectory = Paths.get("src//main//resources//static/img");
			String absolutPath = imageDirectory.toFile().getAbsolutePath();

			try {
				byte[] bytesImg = imagen.getBytes();
				String imageWithExtension = imagen.getOriginalFilename();
				String name = imageWithExtension.substring(0, imageWithExtension.indexOf('.'));
				String extension = imageWithExtension.substring(imageWithExtension.lastIndexOf('.'));
				String imageName = name + "-" + System.currentTimeMillis() + extension;
				Path fullPath = Paths.get(absolutPath + "//" + imageName);
				Files.write(fullPath, bytesImg);
				article.setImage(imageName);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		UsersEntity user = userService.findUserByEmail(auth.getName());
		article.setUsersId(user);

		articleService.createArticle(articleService.transform(article));
		return "redirect:/user/Myarticles";
	}

	@GetMapping("/deleteArticle/{id}")
	public String deleteArticle(Model model, @PathVariable(name = "id") int id) throws Exception {

		articleService.findArticleById(id);
		articleService.deleteArticle(id);
		return "redirect:/user/Myarticles";
	}

	@GetMapping("/updateArticle/{id}")
	public String updateArticleForm(Model model, @PathVariable(name="id") int id) {

		ArticleEntity article = articleService.findArticleById(id);
		model.addAttribute("article", article);
		model.addAttribute("editMode", true);
		model.addAttribute("categories", categoryService.listAllCategory());
		return ViewConstant.UPDATEARTICLE;
	}

	@PostMapping("/updateArticle/{id}")
	public String updateArticle(@Valid @ModelAttribute("article") ArticleEntity articles,
			@PathVariable(name="id") int id, @RequestParam(name = "imgs", required = false) MultipartFile imagen) {

		ArticleEntity article = articleService.findArticleById(id);
		if (!imagen.isEmpty()) {
			Path imageDirectory = Paths.get("src//main//resources//static/img");
			String absolutPath = imageDirectory.toFile().getAbsolutePath();
			File img_old = new File(absolutPath + "//" + article.getImage());
			img_old.delete();

			try {
				byte[] bytesImg = imagen.getBytes();
				String extensionImage = imagen.getOriginalFilename();
				String nombre = extensionImage.substring(0, extensionImage.indexOf('.'));
				String extension = extensionImage.substring(extensionImage.lastIndexOf('.'));
				String image_name = nombre + "-" + System.currentTimeMillis() + extension;
				Path completePath = Paths.get(absolutPath + "//" + image_name);
				Files.write(completePath, bytesImg);
				articles.setImage(image_name);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		articleService.updateArticle(articleService.transform(articles));
		return "redirect:/user/Myarticles";
	}
	
	

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ConfirmationTokenRepository getConfirmationTokenRepository() {
		return confirmationTokenRepository;
	}

	public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	public EmailSenderService getEmailSenderService() {
		return emailSenderService;
	}

	public void setEmailSenderService(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}

}
