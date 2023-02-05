package jointcase.dto.mapper;

public interface ResponseDtoMapper<D, M> {
    D mapToDto(M model);
}
