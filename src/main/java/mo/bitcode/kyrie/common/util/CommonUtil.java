package mo.bitcode.kyrie.common.util;

import mo.bitcode.kyrie.common.exception.KyrieException;
import mo.bitcode.kyrie.common.model.TeamSide;
import mo.bitcode.kyrie.common.model.dto.UserProfileDto;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonUtil {

  public static List<String> getUserIds(List<UserProfileDto> userProfileDtos) {
    return userProfileDtos.stream().map(UserProfileDto::getId).collect(Collectors.toList());
  }

  public static List<String> getUserIds(TeamDto teamDto1, TeamDto teamDto2) {
    return Stream.of(teamDto1.getTeamMemberIds(), teamDto2.getTeamMemberIds())
      .flatMap(Collection::stream)
      .collect(Collectors.toList());
  }

  public static List<String> toString(List<ObjectId> objectIds) {
    return objectIds.stream()
      .map(ObjectId::toString)
      .collect(Collectors.toList());
  }

  public static List<ObjectId> toObjectId(List<String> ids) {
    return ids.stream()
      .map(ObjectId::new)
      .collect(Collectors.toList());
  }

  public static <T> TeamSide getSide(T homeTeamId, T awayTeamId, T currentId) {
    if (currentId == null) {
      // TODO exception
      throw new KyrieException(HttpStatus.INTERNAL_SERVER_ERROR, "500.999", "current team id is null");
    }
    if (homeTeamId.equals(currentId)) {
      return TeamSide.HOME;
    } else if (awayTeamId.equals(currentId)) {
      return TeamSide.AWAY;
    } else {
      // TODO exception
      throw new KyrieException(HttpStatus.INTERNAL_SERVER_ERROR, "500.999", "current team is not belong to any side");
    }
  }

  public static Map<TeamSide, TeamDto> determineTeamSide(String homeTeamId, List<TeamDto> teamDtos) {
    return teamDtos.stream().collect(Collectors.toMap(
      teamDto -> teamDto.getId().equals(homeTeamId) ? TeamSide.HOME : TeamSide.AWAY,
      Function.identity(),
      (a, b) -> a));
  }

  public static <T> Page<T> tranformPage(List<T> content, Page<?> originalPage) {
    return new PageImpl<>(content, originalPage.getPageable(), originalPage.getTotalElements());
  }

}
