package com.user.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.user.entities.Hotel;
import com.user.entities.Rating;
import com.user.externalServices.HotelService;
import com.user.externalServices.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user.customException.ResourceNotFound;
import com.user.entities.User;
import com.user.repo.userRepository;
import com.user.service.UserService;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private userRepository userRepo;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private RatingService ratingService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);



	@Override
	public User saveUser(User user) {
		//generating random user id since we are not generating it automatically
		String id = UUID.randomUUID().toString();

		user.setUserId(id);
		return this.userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return this.userRepo.findAll();
	}

	@Override
	public User getUser(String userId) {
		//Optional is like that gift box. It's a container that might contain
		//a value (like a piece of data) or be empty (no data at all).
		// TODO Auto-generated method stub
		//cant use only findBid either use it with optional or use orElsethrow cuz there will
		//be a type mismatch and findById by default return optional.

		//get user from database with the help pof user repository
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User with given id isnot found on the server"+userId));
		//fetch ratings given by the above user from the Rating Service
		//the restTemplate will convert the incoming json response into arrayList .
//		 Rating[] ratingOfUser= restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		//the abovw approach is fro rest Template and the below one is for the feign client
		Rating[] ratingOfUser = ratingService.getRatingsBYUser(user.getUserId()).toArray(new Rating[0]);

		 logger.info("{}",ratingOfUser);
		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
		/*You're sending an HTTP GET request to a URL that presumably retrieves ratings for a specific user.
You're expecting the response to be an array of Rating objects (Rating[].class).
The getForObject method fetches and converts the JSON response to an array of Rating objects.
You're logging the retrieved ratings using a logger.*/
		 List<Rating> ratingList= ratings.stream().map(rating->{
			 //api call to hotel service to get the hotel
			 //http://localhost:8082/hotel/03f3120f-a323-44c6-8837-093f03332cd4
//			 ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
			 // Hotel hotel = forEntity.getBody();
			 //the above part is using restTemplate and the below part is using the feign client
			 Hotel hotel = hotelService.getSingle(rating.getHotelId());

//			 logger.info("response status code: {}",forEntity.getStatusCode());
			 //set the hotel to rating
			 rating.setHotel(hotel);
			 //return the rating
			 return rating;

			 /*You're using the stream() method on the ratings list to process each Rating object in the list one by one.
For each Rating object, you're making an HTTP GET request to the URL that retrieves hotel information based on the HotelId stored in the rating.
You're using the getForEntity method, which returns a ResponseEntity<Hotel> containing the entire HTTP response, including headers, status code, and the response body (in this case, the Hotel object).
You're extracting the Hotel object from the response using forEntity.getBody().
You're logging the response status code using the logger.
You're updating the Rating object with the fetched Hotel information.
Finally, you're collecting all these updated Rating objects into a new list using the collect(Collectors.toList()) operation.*/


		 }).collect(Collectors.toList());
		 user.setRatings(ratingList);


		return user;

	}

	@Override
	public void deleteUser(String userId) throws NotFoundException{
		// TODO Auto-generated method stub
		this.userRepo.deleteById(userId);
		
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub

		return this.userRepo.save(user);
	}

}
