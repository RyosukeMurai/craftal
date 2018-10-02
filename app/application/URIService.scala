package application

import java.net.URL

import application.ResourceType.ResourceType

object URIService {
  def resolveUrlByIdentifierAndResourceType(identifier: String, resourceType: ResourceType): URL = {
    resourceType match {
      case ResourceType.photo => new URL(s"http://localhost:9000/assets/images/$identifier")
      case _ => throw new IllegalArgumentException(s"Unsupported resource type $resourceType")
    }
  }
}