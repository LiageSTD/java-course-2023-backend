package edu.java.bot.service.linksHandler.links;

public record StackOverFlowLink(String host, String path) implements Link {
    @Override
    public String host() {
        return host;
    }

    @Override
    public String path() {
        return path;
    }
}
