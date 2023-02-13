package com.pintalk.account.component;

import com.pintalk.account.entity.Account;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AccountSpecification {

    public static Specification<Account> searchWith(Map<String, Object> searchKeyword) {
        return (Specification<Account>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }

    private static List<Predicate> getPredicateWithKeyword(Map<String, Object> searchKeyword, Root<Account> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();

        for (String key : searchKeyword.keySet()) {
            if (key.equals("holder_name") || key.equals("account_no") || key.equals("address1") || key.equals("email") || key.equals("phone2") || key.equals("phone3")) {
                predicate.add(builder.like(root.get(key), "%" + searchKeyword.get(key) + "%"));
            } else {
                predicate.add(builder.equal(root.get(key), searchKeyword.get(key)));
            }
        }
        return predicate;
    }

}