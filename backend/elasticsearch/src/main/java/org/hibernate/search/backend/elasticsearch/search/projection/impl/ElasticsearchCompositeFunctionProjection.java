/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.backend.elasticsearch.search.projection.impl;

import java.util.function.Function;

import org.hibernate.search.backend.elasticsearch.search.impl.ElasticsearchSearchContext;

class ElasticsearchCompositeFunctionProjection<P1, P>
		extends AbstractElasticsearchCompositeProjection<P> {

	private final Function<P1, P> transformer;

	ElasticsearchCompositeFunctionProjection(ElasticsearchSearchContext searchContext,
			Function<P1, P> transformer,
			ElasticsearchSearchProjection<?, P1> projection) {
		super( searchContext, projection );
		this.transformer = transformer;
	}

	@Override
	@SuppressWarnings("unchecked")
	P doTransform(Object[] childResults) {
		return transformer.apply( (P1) childResults[0] );
	}
}
