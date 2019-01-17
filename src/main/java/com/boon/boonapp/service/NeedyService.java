package com.boon.boonapp.service;

import com.boon.boonapp.dao.NeedyRepository;
import com.boon.boonapp.exception.NeedyNotFoundException;
import com.boon.boonapp.model.Location;
import com.boon.boonapp.model.Needy;
import com.boon.boonapp.model.NeedyType;
import com.boon.boonapp.model.User;
import com.boon.boonapp.service.util.LocationUtils;
import com.google.common.collect.Sets;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NeedyService {

    private final NeedyRepository needyRepository;
    private final UserService userService;
    private final LocationService locationService;

    @Autowired
    public NeedyService(NeedyRepository needyRepository, UserService userService, LocationService locationService) {
        this.needyRepository = needyRepository;
        this.userService = userService;
        this.locationService = locationService;
    }

    public Needy findNeedyById(Long needyId) {
        return needyRepository.findById(needyId)
                .orElseThrow(() -> new NeedyNotFoundException(needyId));
    }

    public Needy createNewNeedy(Needy needy, User creator) {
        if (needy.getId() != null) {
            throw new IllegalArgumentException("Needy Id must not be present on create");
        }
        if (needy.getAttachedUsers() == null) {
            needy.setAttachedUsers(Sets.newHashSet());
        }
        //Add creator as attached user
        if (needy.getAttachedUsers().stream().noneMatch(user -> user.getEmail().equals(creator.getEmail()))) {
            needy.getAttachedUsers().add(creator);
        }
        return save(needy);
    }

    public Needy save(Needy needy) {
        return needyRepository.save(needy);
    }

    public List<Needy> getAllNeediesInAreaAndType(@Nullable Location location, @Nullable Double distance, @Nullable NeedyType type, Integer count) {
        List<Needy> needies = needyRepository.findAll();
        if (location != null && distance != null) {
            return needies.stream()
                    .filter(n -> (n.getType() == type || type == null))
                    .filter(n -> LocationUtils.calculateDistanceBetweenPoints(location.getLat(), location.getLng(), n.getLocation().getLat(), n.getLocation().getLng()) <= distance)
                    .sorted((n1, n2) -> {
                        Double distanceOne = LocationUtils.calculateDistanceBetweenPoints(location.getLat(), location.getLng(), n1.getLocation().getLat(), n1.getLocation().getLng());
                        Double distanceTwo = LocationUtils.calculateDistanceBetweenPoints(location.getLat(), location.getLng(), n2.getLocation().getLat(), n2.getLocation().getLng());
                        return distanceOne.compareTo(distanceTwo);
                    }).limit(count).collect(Collectors.toList());
        }
        return needies.stream().filter(n -> (n.getType() == type || type == null)).limit(count).collect(Collectors.toList());

    }
}
