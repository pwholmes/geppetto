package org.geppetto.domain.declaration;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>EntityTest</code> contains tests for the class <code>{@link Entity}</code>.
 *
 * @generatedBy CodePro at 5/12/13 8:48 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class EntityTest {
	/**
	 * Run the Entity() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Test
	public void testEntity_1()
		throws Exception {

		Entity result = new Entity();

		// add additional test code here

		assertNull(result.getName());
	}

	/**
	 * Run the Entity(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Test
	public void testEntity_2()
		throws Exception {
		String name = "name";

		Entity result = new Entity(name);

		// add additional test code here
	
		assertEquals(result.getName(), "name");
	}

	/**
	 * Run the void addProperties(List<Property>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Test
	public void testAddProperties_1()
		throws Exception {
		Entity fixture = new Entity("entityName");
//		fixture.properties = new ArrayList();
		List<Property> properties = new ArrayList<Property>();
		PropertyDefinition pd0 = new PropertyDefinition("pd0");
		PropertyDefinition pd1 = new PropertyDefinition("pd1");
		PropertyDefinition pd2 = new PropertyDefinition("pd2");
		PropertyDefinition pd3 = new PropertyDefinition("pd3");
		
		Property p0 = new Property("p0", pd0);
		Property p1 = new Property("p1", pd1);
		Property p2 = new Property("p2", pd2);
		Property p3 = new Property("p3", pd3);

		
		properties.add(p0);
		properties.add(p1);
		properties.add(p2);
		properties.add(p3);
		

		
		fixture.addProperties(properties);

		assertEquals(properties, fixture.getProperties());
		// add additional test code here
	}

	/**
	 * Run the void addProperty(Property) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Test
	public void testAddProperty_1()
		throws Exception {
		Entity fixture = new Entity("");
		fixture.properties = new ArrayList();
		Property property = new Property("", new PropertyDefinition(""));

		fixture.addProperty(property);

		// add additional test code here
		assertNotNull(fixture.getProperties().get(0));
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		Entity fixture = new Entity("");
		fixture.properties = new ArrayList();

		String result = fixture.getName();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the List<Property> getProperties() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Test
	public void testGetProperties_1()
		throws Exception {
		Entity fixture = new Entity("");
		fixture.properties = new ArrayList();

		List<Property> result = fixture.getProperties();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Property getProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Test
	public void testGetProperty_1()
		throws Exception {
		Entity fixture = new Entity("");
//		fixture.properties = new ArrayList();
		Property propertyOne = new Property("propertyName", new PropertyDefinition("propertyDefName"));
		fixture.addProperty( propertyOne);
//		String name = "a";

		Property result = fixture.getProperty("propertyName");

		// add additional test code here
		assertEquals(result, propertyOne);
	}


	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		Entity fixture = new Entity("entityName");

		String result = fixture.toString();
		// add additional test code here
		assertEquals(result, "{Entity: name: entityName; properties: ()}");
	}

	

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@Before
	public void setUp()
		throws Exception {
		
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 5/12/13 8:48 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(EntityTest.class);
	}
}