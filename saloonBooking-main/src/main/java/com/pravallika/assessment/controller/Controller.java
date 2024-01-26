package com.pravallika.assessment.controller;

import com.pravallika.assessment.model.Appointment;
import com.pravallika.assessment.model.AuthRequest;
import com.pravallika.assessment.model.SaloonItem;
import com.pravallika.assessment.model.User;
import com.pravallika.assessment.service.AppointmentService;
import com.pravallika.assessment.service.JwtService;
import com.pravallika.assessment.service.SaloonItemService;
import com.pravallika.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private SaloonItemService saloonItemService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/common/register")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User u=userService.register(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PostMapping("/common/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("Invalid User");
        }
    }

    @PutMapping("/common/changepassword")
    public ResponseEntity<User> changePassword(@RequestParam String username,@RequestParam String password){
        User user = userService.changePassword(username,password);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }


    @PostMapping(value = "/admin/additem")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SaloonItem> saveItem(@RequestBody SaloonItem saloonItem){
        SaloonItem item= saloonItemService.addItem(saloonItem);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/updateitem")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SaloonItem> updateItem(@RequestBody SaloonItem saloonItem){
        SaloonItem item= saloonItemService.updateItem(saloonItem);
        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/admin/deleteitem")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SaloonItem> deleteItem(@RequestParam String category){
        SaloonItem item= saloonItemService.deleteItem(category);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(value ="/user/bookappointment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment,@RequestParam String category){
        SaloonItem item= saloonItemService.findByCategory(category);
        appointment.setSaloon_item(item);
        appointmentService.bookAppointment(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @GetMapping(value="/getAppointments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Appointment>> getAppointments(@RequestParam LocalDate date){
        List<Appointment> appointments = appointmentService.getAppointments(date);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
