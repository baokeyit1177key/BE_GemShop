package online.gemfpt.BE.model;

import lombok.Data;
import online.gemfpt.BE.Entity.Account;

@Data
public class AccountResponse extends Account {
    String token;
}
