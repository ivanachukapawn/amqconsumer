package test.com.jms.listener;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jms.adapter.ConsumerAdapter;

public class ConsumerAdapterTest
{
	private	String	json	=	"{vendorName:\"Microsofttest\",firstName:\"BobTest\",lastName:\"SmithTest\",address:\"123 Main test\",city:\"TulsaTest\",state:\"OKTest\",zip:\"71345Test\",email:\"Bob@microsoft.test\",phoneNumber:\"test-123-test\"}";

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testSendToMongo()
	{
		ConsumerAdapter	adapter	=	new	ConsumerAdapter ();
		adapter.sendToMongo(json);
		assertNotNull(json);
	}

}
