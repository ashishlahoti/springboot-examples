package com.example.demo.actuator;

import lombok.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Endpoint(id = "release-notes")
public class ReleaseNotesEndpoint {

    @ReadOperation
    public ReleaseNotes releaseNotes() {
        return new ReleaseNotes(List.of(
            new ReleaseNote("1.0", List.of(
                "Homepage Added",
                "Item creation form added",
                "View the watchlist page added")),
            new ReleaseNote("2.0", List.of(
                "Workspace page Added",
                "Add task form created"))));
    }
}

@Value
class ReleaseNotes {
    List<ReleaseNote> releaseNotes;
}

@Value
class ReleaseNote {
    String version;
    List<String> items;
}
