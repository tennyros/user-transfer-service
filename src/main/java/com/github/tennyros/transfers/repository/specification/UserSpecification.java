package com.github.tennyros.transfers.repository.specification;

import com.github.tennyros.transfers.entity.EmailData;
import com.github.tennyros.transfers.entity.PhoneData;
import com.github.tennyros.transfers.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {

    private final String name;
    private final LocalDate dateOfBirth;
    private final String email;
    private final String phone;

    @Override
    public Predicate toPredicate(@NonNull Root<User> root,
                                 CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(name)) {
            predicates.add(cb.like(root.get("name"), name + "%"));
        }

        if (dateOfBirth != null) {
            predicates.add(cb.greaterThan(root.get("dateOfBirth"), dateOfBirth));
        }

        if (StringUtils.hasText(email)) {
            Join<User, EmailData> userEmailData = root.join("userEmails", JoinType.INNER);
            predicates.add(cb.equal(userEmailData.get("email"), email));
        }

        if (StringUtils.hasText(phone)) {
            Join<User, PhoneData> phoneData = root.join("userPhones", JoinType.INNER);
            predicates.add(cb.equal(phoneData.get("phone"), phone));
        }

        query.distinct(true);
        return cb.and(predicates.toArray(new Predicate[0]));
    }

}

