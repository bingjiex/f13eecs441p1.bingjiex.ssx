package umich.eecs441.project.proto;

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: insert_command_buf.proto

public final class InsertCommandBuf {
  private InsertCommandBuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface InsertCommandBufObjOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 clientID = 1;
    /**
     * <code>required int32 clientID = 1;</code>
     */
    boolean hasClientID();
    /**
     * <code>required int32 clientID = 1;</code>
     */
    int getClientID();

    // required string newChar = 2;
    /**
     * <code>required string newChar = 2;</code>
     */
    boolean hasNewChar();
    /**
     * <code>required string newChar = 2;</code>
     */
    java.lang.String getNewChar();
    /**
     * <code>required string newChar = 2;</code>
     */
    com.google.protobuf.ByteString
        getNewCharBytes();
  }
  /**
   * Protobuf type {@code InsertCommandBufObj}
   */
  public static final class InsertCommandBufObj extends
      com.google.protobuf.GeneratedMessage
      implements InsertCommandBufObjOrBuilder {
    // Use InsertCommandBufObj.newBuilder() to construct.
    private InsertCommandBufObj(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private InsertCommandBufObj(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final InsertCommandBufObj defaultInstance;
    public static InsertCommandBufObj getDefaultInstance() {
      return defaultInstance;
    }

    public InsertCommandBufObj getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private InsertCommandBufObj(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              clientID_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              newChar_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return InsertCommandBuf.internal_static_InsertCommandBufObj_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return InsertCommandBuf.internal_static_InsertCommandBufObj_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              InsertCommandBuf.InsertCommandBufObj.class, InsertCommandBuf.InsertCommandBufObj.Builder.class);
    }

    public static com.google.protobuf.Parser<InsertCommandBufObj> PARSER =
        new com.google.protobuf.AbstractParser<InsertCommandBufObj>() {
      public InsertCommandBufObj parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new InsertCommandBufObj(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<InsertCommandBufObj> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 clientID = 1;
    public static final int CLIENTID_FIELD_NUMBER = 1;
    private int clientID_;
    /**
     * <code>required int32 clientID = 1;</code>
     */
    public boolean hasClientID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 clientID = 1;</code>
     */
    public int getClientID() {
      return clientID_;
    }

    // required string newChar = 2;
    public static final int NEWCHAR_FIELD_NUMBER = 2;
    private java.lang.Object newChar_;
    /**
     * <code>required string newChar = 2;</code>
     */
    public boolean hasNewChar() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string newChar = 2;</code>
     */
    public java.lang.String getNewChar() {
      java.lang.Object ref = newChar_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          newChar_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string newChar = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNewCharBytes() {
      java.lang.Object ref = newChar_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        newChar_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      clientID_ = 0;
      newChar_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasClientID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasNewChar()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, clientID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getNewCharBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, clientID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getNewCharBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static InsertCommandBuf.InsertCommandBufObj parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static InsertCommandBuf.InsertCommandBufObj parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(InsertCommandBuf.InsertCommandBufObj prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code InsertCommandBufObj}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements InsertCommandBuf.InsertCommandBufObjOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return InsertCommandBuf.internal_static_InsertCommandBufObj_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return InsertCommandBuf.internal_static_InsertCommandBufObj_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                InsertCommandBuf.InsertCommandBufObj.class, InsertCommandBuf.InsertCommandBufObj.Builder.class);
      }

      // Construct using InsertCommandBuf.InsertCommandBufObj.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        clientID_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        newChar_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return InsertCommandBuf.internal_static_InsertCommandBufObj_descriptor;
      }

      public InsertCommandBuf.InsertCommandBufObj getDefaultInstanceForType() {
        return InsertCommandBuf.InsertCommandBufObj.getDefaultInstance();
      }

      public InsertCommandBuf.InsertCommandBufObj build() {
        InsertCommandBuf.InsertCommandBufObj result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public InsertCommandBuf.InsertCommandBufObj buildPartial() {
        InsertCommandBuf.InsertCommandBufObj result = new InsertCommandBuf.InsertCommandBufObj(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.clientID_ = clientID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.newChar_ = newChar_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof InsertCommandBuf.InsertCommandBufObj) {
          return mergeFrom((InsertCommandBuf.InsertCommandBufObj)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(InsertCommandBuf.InsertCommandBufObj other) {
        if (other == InsertCommandBuf.InsertCommandBufObj.getDefaultInstance()) return this;
        if (other.hasClientID()) {
          setClientID(other.getClientID());
        }
        if (other.hasNewChar()) {
          bitField0_ |= 0x00000002;
          newChar_ = other.newChar_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasClientID()) {
          
          return false;
        }
        if (!hasNewChar()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        InsertCommandBuf.InsertCommandBufObj parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (InsertCommandBuf.InsertCommandBufObj) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 clientID = 1;
      private int clientID_ ;
      /**
       * <code>required int32 clientID = 1;</code>
       */
      public boolean hasClientID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 clientID = 1;</code>
       */
      public int getClientID() {
        return clientID_;
      }
      /**
       * <code>required int32 clientID = 1;</code>
       */
      public Builder setClientID(int value) {
        bitField0_ |= 0x00000001;
        clientID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 clientID = 1;</code>
       */
      public Builder clearClientID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        clientID_ = 0;
        onChanged();
        return this;
      }

      // required string newChar = 2;
      private java.lang.Object newChar_ = "";
      /**
       * <code>required string newChar = 2;</code>
       */
      public boolean hasNewChar() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string newChar = 2;</code>
       */
      public java.lang.String getNewChar() {
        java.lang.Object ref = newChar_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          newChar_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string newChar = 2;</code>
       */
      public com.google.protobuf.ByteString
          getNewCharBytes() {
        java.lang.Object ref = newChar_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          newChar_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string newChar = 2;</code>
       */
      public Builder setNewChar(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        newChar_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string newChar = 2;</code>
       */
      public Builder clearNewChar() {
        bitField0_ = (bitField0_ & ~0x00000002);
        newChar_ = getDefaultInstance().getNewChar();
        onChanged();
        return this;
      }
      /**
       * <code>required string newChar = 2;</code>
       */
      public Builder setNewCharBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        newChar_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:InsertCommandBufObj)
    }

    static {
      defaultInstance = new InsertCommandBufObj(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:InsertCommandBufObj)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_InsertCommandBufObj_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_InsertCommandBufObj_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030insert_command_buf.proto\"8\n\023InsertComm" +
      "andBufObj\022\020\n\010clientID\030\001 \002(\005\022\017\n\007newChar\030\002" +
      " \002(\t"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_InsertCommandBufObj_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_InsertCommandBufObj_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_InsertCommandBufObj_descriptor,
              new java.lang.String[] { "ClientID", "NewChar", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
