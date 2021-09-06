package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sun.tools.javac.util.List;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServerAddressTest {

  @Test
  public void testValidateURLWhenDuplicateUrl() throws MalformedURLException {
    Collection<URL> urls = List.of(new URL("http://test2.com"),
        new URL("http://test.com"), new URL("http://test2.com"));

    IllegalArgumentException illegalArgumentException =
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ServerAddress(urls));
    assertEquals("There are duplicated URLs", illegalArgumentException.getMessage());
  }

  @Test
  public void testValidateUrlWhenNoDuplication() throws MalformedURLException {
    Collection<URL> urls = List.of(new URL("http://test1.com"),
        new URL("http://test2.com"), new URL("http://test3.com"));

    Assertions.assertDoesNotThrow(() -> new ServerAddress(urls));
  }

  @Test
  public void testValidateUrlWhen10Urls() throws MalformedURLException {
    Collection<URL> urls = getUrls();

    Assertions.assertDoesNotThrow(() -> new ServerAddress(urls));
  }

  @Test
  public void testValidateURLWhenMoreThan10Urls() throws MalformedURLException {
    Collection<URL> urls = getUrls();
    urls.add(new URL("http://test.com/10"));

    IllegalArgumentException illegalArgumentException =
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ServerAddress(urls));
    assertEquals("The amount of URLs shouldn't be greater than 10",
        illegalArgumentException.getMessage());
  }

  @Test
  public void testValidateURLWhenURLsAreEmpty() {
    Collection<URL> urls = new ArrayList<>();

    IllegalArgumentException illegalArgumentException =
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ServerAddress(urls));
    assertEquals("The urls shouldn't be empty", illegalArgumentException.getMessage());
  }

  private Collection<URL> getUrls() throws MalformedURLException {
    Collection<URL> urls = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      urls.add(new URL("http://test.com/" + i));
    }
    return urls;
  }

}