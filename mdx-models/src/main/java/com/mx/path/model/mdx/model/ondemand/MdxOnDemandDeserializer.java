package com.mx.path.model.mdx.model.ondemand;

import com.mx.path.core.common.model.ModelWrappable;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonPointer;
import tools.jackson.core.TreeNode;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ValueDeserializer;

/**
 * Custom deserializer for MDX OnDemand Resources
 * <p>
 * Digs out the node using the nodePath locator, then deserializes.
 *
 * @param <T>
 */
public class MdxOnDemandDeserializer<T extends ModelWrappable<?>> extends ValueDeserializer<T> {
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private final String nodePath;
  private final Class<?> target;

  @SuppressWarnings("unchecked")
  public MdxOnDemandDeserializer(Class<?> target, String nodePath) {
    super();
    this.target = target;
    this.nodePath = nodePath;
  }

  @Override
  @SuppressWarnings("unchecked")
  public final T deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
    TreeNode root = p.readValueAsTree();
    TreeNode value = root.at(JsonPointer.compile(nodePath));

    return (T) MAPPER.convertValue(value, target);
  }
}
