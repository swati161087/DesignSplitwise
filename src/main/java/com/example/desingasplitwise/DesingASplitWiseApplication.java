package com.example.desingasplitwise;

import com.example.desingasplitwise.controllers.ExpenceController;
import com.example.desingasplitwise.controllers.GroupController;
import com.example.desingasplitwise.controllers.SettelUpController;
import com.example.desingasplitwise.controllers.UserController;
import com.example.desingasplitwise.dtos.AddExpenceDto;
import com.example.desingasplitwise.dtos.AddGroupDto;
import com.example.desingasplitwise.dtos.RegisterUserDto;
import com.example.desingasplitwise.models.Currency;
import com.example.desingasplitwise.models.Transaction;
import com.example.desingasplitwise.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DesingASplitWiseApplication implements CommandLineRunner {
    @Autowired
    private UserController userController;
    @Autowired
    private GroupController groupController;
    @Autowired
    private ExpenceController expenceController;
    @Autowired
    private SettelUpController settelUpController;

    public static void main(String[] args) {
        SpringApplication.run(DesingASplitWiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RegisterUserDto registerUserDto=new RegisterUserDto();
        registerUserDto.setName("Mita");
        registerUserDto.setPassword("pqrst");
     this.userController.registerUser(registerUserDto);
     String token=this.userController.login(6l,"pqrst");
     System.out.println(token);
        AddGroupDto addGroupDto=new AddGroupDto();
        addGroupDto.setGroupName("GoaTrip");
        addGroupDto.setUserId(1L);
        addGroupDto.setParticipants(List.of(2L,3L,4L));
        //this.groupController.addGroup(addGroupDto);
        AddExpenceDto addExpenceDto=new AddExpenceDto();
        addExpenceDto.setAmount(2000);
        addExpenceDto.setCurrency(Currency.USD);
        addExpenceDto.setDescription("expance on restorant");
        addExpenceDto.setGroupId(1L);
        addExpenceDto.setParticipants(List.of(1L,2L,3L));
        Map<Long,Double> whoOwes=new HashMap<>();
        whoOwes.put(1L,1000.0);
        whoOwes.put(2l,500.0);
        whoOwes.put(3l,500.0);
        Map<Long,Double> whoPaid=new HashMap<>();
        whoPaid.put(2l,1500.0);
        whoPaid.put(3l,500.0);
        addExpenceDto.setUserId(1l);
        addExpenceDto.setWhoOwes(whoOwes);
        addExpenceDto.setWhoPaid(whoPaid);
        //this.expenceController.addExpenceToGroup(addExpenceDto);
        List<Transaction> transactions=this.settelUpController.settelGroup(1L);
    }
}
