package ch.saldo.config

import graphql.scalars.ExtendedScalars
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class GraphQLConfig : RuntimeWiringConfigurer {
    override fun configure(builder: RuntimeWiring.Builder) {
        builder.scalar(ExtendedScalars.Date)
    }
}
