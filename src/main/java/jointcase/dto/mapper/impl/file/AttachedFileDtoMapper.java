package jointcase.dto.mapper.impl.file;

import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.file.AttachedFileResponseDto;
import jointcase.model.AttachedFile;
import org.springframework.stereotype.Component;

@Component
public class AttachedFileDtoMapper
        implements ResponseDtoMapper<AttachedFileResponseDto, AttachedFile> {
    @Override
    public AttachedFileResponseDto mapToDto(AttachedFile attachedFile) {
        return AttachedFileResponseDto.builder()
                .id(attachedFile.getId())
                .name(attachedFile.getName())
                .type(attachedFile.getType())
                .build();
    }
}
