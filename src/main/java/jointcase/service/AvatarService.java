package jointcase.service;

import jointcase.model.Avatar;

public interface AvatarService {
    Avatar add(Avatar avatar);

    Avatar get(Long id);
}
