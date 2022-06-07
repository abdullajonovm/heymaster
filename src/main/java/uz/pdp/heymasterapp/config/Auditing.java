package uz.pdp.heymasterapp.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.heymasterapp.entity.User;

import java.util.Optional;

public class Auditing implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null &&
            authentication.isAuthenticated() &&
            !authentication.getPrincipal().equals("anonymousUser")){
            User currentUser = (User) authentication.getPrincipal();
            return Optional.of(currentUser.getId());
        }
        return Optional.empty();
    }
}
