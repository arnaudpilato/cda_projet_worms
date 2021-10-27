package org.wcscda.worms;

import static org.junit.Assert.*;

import junit.framework.TestCase;

public class ConfigTest extends TestCase {
  public void testGetRecordGame() {
    assertTrue(Config.getRecordGame() instanceof Boolean);
  }
}
