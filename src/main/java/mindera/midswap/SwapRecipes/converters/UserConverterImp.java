package mindera.midswap.SwapRecipes.converters;

import mindera.midswap.SwapRecipes.commands.UserDto;
import mindera.midswap.SwapRecipes.commands.UserUpdateDto;
import mindera.midswap.SwapRecipes.persistence.models.Recipe;
import mindera.midswap.SwapRecipes.persistence.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;



//@AllArgsConstructor
@Component
public class UserConverterImp implements UserConverterI{

    private final ModelMapper modelMapper;

    @Autowired
    public UserConverterImp(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public UserDto entityToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    @Override   //ing                                   api
    public List<UserDto> entityListToDtoList(List<User> userList) {
        return userList.stream()
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> DtoListToEntityList(List<UserDto> userDtos) {
        return null;
    }

    @Override
    public UserUpdateDto entityToUpdateDto(User user) {
        UserUpdateDto updateDto = this.modelMapper.map(user, UserUpdateDto.class);
        return updateDto;
    }

    @Override
    public UserUpdateDto entityToUpdateFavouritesDto(User user, Recipe recipe) {
        user.addFavouriteRecipeId(recipe);
        UserUpdateDto updateDto = this.modelMapper.map(user, UserUpdateDto.class);
        return updateDto;
    }

    @Override
    public User updateDtoToEntity(UserUpdateDto userUpdateDto, User user) {
        this.modelMapper.map(userUpdateDto, user);
        return user; //a entity é que está a fazer o update
    }


}
