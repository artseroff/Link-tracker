package edu.java.response;

import java.util.List;

public record ListLinksResponse(
    List<LinkResponse> list
) {
}
