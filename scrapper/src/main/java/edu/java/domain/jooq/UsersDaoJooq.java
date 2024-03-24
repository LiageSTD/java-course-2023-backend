//package edu.java.domain.jooq;
//
//import edu.java.domain.dao.UsersDao;
//
//import edu.java.dto.model.User;
//import jakarta.validation.constraints.NotNull;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.jooq.DSLContext;
//@RequiredArgsConstructor
//public class UsersDaoJooq implements UsersDao {
//    private final DSLContext dslContext;
//
//    @Override
//    public boolean add(User user) {
//        return dslContext.insertInto(USERS)
//                .set(USERS.ID, user.getId())
//                .set(USERS.NAME, user.getName())
//                .set(USERS.EMAIL, user.getEmail())
//                .execute() == 1;
//    }
//
//    @Override
//    public void remove(User user) {
//
//    }
//
//    @Override
//    public boolean update(User user, User newUser) {
//        return false;
//    }
//
//    @Override
//    public List<@NotNull User> getAll() {
//        return null;
//    }
//}
