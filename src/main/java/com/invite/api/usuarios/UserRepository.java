package com.invite.api.usuarios;

import com.invite.api.usuarios.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
