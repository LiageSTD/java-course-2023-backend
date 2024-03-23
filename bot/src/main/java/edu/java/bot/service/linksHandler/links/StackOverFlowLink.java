package edu.java.bot.service.linksHandler.links;

public record StackOverFlowLink(String host, String path) implements Link {
    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }
}
