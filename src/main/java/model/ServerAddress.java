package model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class ServerAddress {

  private final List<URL> urls;
  private final Random random = new Random();

  public ServerAddress(final Collection<URL> urls) {
    validateUrl(urls);
    this.urls = new ArrayList<>(urls);
  }

  private void validateUrl(Collection<URL> urls) {
    if (urls.isEmpty()) {
      throw new IllegalArgumentException("The urls shouldn't be empty");
    }

    if (urls.size() > 10) {
      throw new IllegalArgumentException("The amount of URLs shouldn't be greater than 10");
    }

    Set<URL> items = new HashSet<>();
    boolean hasDuplicateElements = !urls.stream()
        .filter(n -> !items.add(n))
        .collect(Collectors.toSet()).isEmpty();

    if (hasDuplicateElements) {
      throw new IllegalArgumentException("There are duplicated URLs");
    }
  }

  public URL get() {
    int i = random.nextInt(urls.size());
    return urls.get(i);
  }
}
