package kr.co.project.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.project.domain.BookingVO;
import kr.co.project.service.BookingService;



@Controller
public class BookingController {

	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Inject
	private BookingService service;
	
	//cart
		@RequestMapping(value = "/cart.do", method = RequestMethod.POST)
		public String cart(@RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout,@RequestParam("addr") String addr,@RequestParam("board_id") int board_id, @RequestParam("people") String people,Model model ) throws Exception {
	//userid, boardid, date, 받아와야한다
			logger.info("cartpage");
			System.out.println(checkin+"   "+checkout);
			model.addAttribute("checkin", checkin);
			model.addAttribute("checkout", checkout);
			model.addAttribute("addr", addr);
			model.addAttribute("people", people);
			model.addAttribute("board_id", board_id);
			
			return "cart";
		}
		
	//confirm
		@RequestMapping(value = "/confirm.do", method = RequestMethod.POST )
		public String confirm(@ModelAttribute BookingVO bkvo, @RequestParam("addr") String addr, Model model) throws Exception {
			logger.info("confirmpage");
			
			System.out.println(bkvo.toString());
			
			
			try {
				service.insertBooking(bkvo);	
				model.addAttribute("bookingVO", bkvo);
				model.addAttribute("addr", addr);
				System.out.println(addr);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return "confirmation";
			
		}
}
