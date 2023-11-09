package pod.tsu.spring.bookstore.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("UnitTest")
class AuthorProtoTest {

    @Test
    public void proto_builder_returnsAuthor() {

        // Given
        int id = 1100;
        String name = "John";
        String email = "johndoek@gmail.com";

        // When
        AuthorProto.Author author = AuthorProto.Author.newBuilder()
            .setId(id)
            .setName(name)
            .setEmail(email)
            .build();

        // Then
        assertEquals(id, author.getId());
        assertEquals(name, author.getName());
        assertEquals(email, author.getEmail());

    }

    @Test
    public void proto_toByteArray_returnsSerializedAuthor() {

        // Given
        AuthorProto.Author author = AuthorProto.Author.newBuilder()
            .setId(1100)
            .setName("John")
            .setEmail("johndoe@gmail.com")
            .build();

        // When
        byte[] serialized = author.toByteArray();

        // Then
        assertTrue(serialized.length > 0);

    }

    @Test
    public void proto_parseFrom_returnsDeserializedAuthor() throws InvalidProtocolBufferException {

        // Given
        int id = 1100;
        String name = "John";
        String email = "johndoek@gmail.com";
        AuthorProto.Author author = AuthorProto.Author.newBuilder()
            .setId(id)
            .setName(name)
            .setEmail(email)
            .build();
        byte[] serialized = author.toByteArray();

        // When
        AuthorProto.Author deserialized = AuthorProto.Author.parseFrom(serialized);

        // Then
        assertNotNull(deserialized);
        assertEquals(id, deserialized.getId());
        assertEquals(name, deserialized.getName());
        assertEquals(email, deserialized.getEmail());

    }

}