package edu.java.bot.client;
//
//import edu.java.dto.scrapper.request.AddLinkRequest;
//import edu.java.dto.scrapper.request.RemoveLinkRequest;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.reactive.function.client.WebClient;
//@AllArgsConstructor
//public class ScrapperClientImpl {
//    private final WebClient client;
//
//    public void registerChat(String id) {
//        client.post()
//            .uri("/tg-chat/{id}",id)
//            .retrieve()
//            .bodyToMono(Void.class)
//            .block();
//    }
//    public void removeChat(String id) {
//        client.delete()
//            .uri("/tg-chat/{id}",id)
//            .retrieve()
//            .bodyToMono(Void.class)
//            .block();
//    }
//    public void getLinks(String id) {
//        client.get()
//            .uri("/list")
//            .header("Tg-Chat-Id",id)
//            .retrieve()
//            .bodyToMono(Void.class)
//            .block();
//    }
//    public void addLink(String id, AddLinkRequest addLinkRequest) {
//        client.post()
//            .uri("/list")
//            .header("Tg-Chat-Id",id)
//            .bodyValue(addLinkRequest)
//            .retrieve()
//            .bodyToMono(Void.class)
//            .block();
//    }
//    public void deleteLink(String id , RemoveLinkRequest removeLinkRequest) {
//        client.method(HttpMethod.DELETE)
//            .uri("/list")
//            .header("Tg-Chat-Id",id)
//            .bodyValue(removeLinkRequest)
//            .retrieve()
//            .bodyToMono(Void.class)
//            .block();
//    }
//}
