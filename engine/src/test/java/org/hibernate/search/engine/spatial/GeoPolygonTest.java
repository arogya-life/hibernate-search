/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.engine.spatial;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

public class GeoPolygonTest {

	@Test
	public void validPolygon() {
		GeoPolygon polygon = GeoPolygon.of( GeoPoint.of( 26, 23 ), GeoPoint.of( 26, 26 ), GeoPoint.of( 24, 26 ),
				GeoPoint.of( 24, 23 ), GeoPoint.of( 26, 23 ) );
		assertNotNull( polygon );

		polygon = GeoPolygon.of( Arrays.asList( GeoPoint.of( 26, 23 ), GeoPoint.of( 26, 26 ), GeoPoint.of( 24, 26 ),
				GeoPoint.of( 24, 23 ), GeoPoint.of( 26, 23 ) ) );
		assertNotNull( polygon );
	}

	@Test
	public void invalidPolygon() {
		assertThatThrownBy(
				() -> GeoPolygon.of(
						GeoPoint.of( 26, 23 ),
						GeoPoint.of( 26, 26 ),
						GeoPoint.of( 24, 26 ),
						GeoPoint.of( 24, 23 )
				)
		)
				.isInstanceOf( IllegalArgumentException.class )
				.hasMessageContaining( "HSEARCH000516" );

		assertThatThrownBy(
				() -> GeoPolygon.of( Arrays.asList(
						GeoPoint.of( 26, 23 ),
						GeoPoint.of( 26, 26 ),
						GeoPoint.of( 24, 26 ),
						GeoPoint.of( 24, 23 )
				) )
		)
				.isInstanceOf( IllegalArgumentException.class )
				.hasMessageContaining( "HSEARCH000516" );
	}
}
