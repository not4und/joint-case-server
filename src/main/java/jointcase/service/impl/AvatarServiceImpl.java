package jointcase.service.impl;

import jointcase.dao.AvatarDao;
import jointcase.model.Avatar;
import jointcase.service.AvatarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final AvatarDao avatarDao;

    @Override
    public Avatar add(Avatar avatar) {
        return avatarDao.add(avatar);
    }

    @Override
    public Avatar get(Long userId) {
        return avatarDao.get(userId).orElseThrow(
                () -> new RuntimeException("Avatar with id: " + userId + "not found.")
        );
    }
}
