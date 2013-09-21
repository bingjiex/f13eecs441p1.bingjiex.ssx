package umich.eecs441.project.proto;

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cursor_command_buf.proto

public final class CursorCommandBuf {
  private CursorCommandBuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CursorCommandBufObjOrBuilder
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

    // required sint32 movement = 2;
    /**
     * <code>required sint32 movement = 2;</code>
     */
    boolean hasMovement();
    /**
     * <code>required sint32 movement = 2;</code>
     */
    int getMovement();
  }
  /**
   * Protobuf type {@code CursorCommandBufObj}
   */
  public static final class CursorCommandBufObj extends
      com.google.protobuf.GeneratedMessage
      implements CursorCommandBufObjOrBuilder {
    // Use CursorCommandBufObj.newBuilder() to construct.
    private CursorCommandBufObj(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CursorCommandBufObj(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CursorCommandBufObj defaultInstance;
    public static CursorCommandBufObj getDefaultInstance() {
      return defaultInstance;
    }

    public CursorCommandBufObj getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CursorCommandBufObj(
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
            case 16: {
              bitField0_ |= 0x00000002;
              movement_ = input.readSInt32();
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
      return CursorCommandBuf.internal_static_CursorCommandBufObj_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CursorCommandBuf.internal_static_CursorCommandBufObj_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CursorCommandBuf.CursorCommandBufObj.class, CursorCommandBuf.CursorCommandBufObj.Builder.class);
    }

    public static com.google.protobuf.Parser<CursorCommandBufObj> PARSER =
        new com.google.protobuf.AbstractParser<CursorCommandBufObj>() {
      public CursorCommandBufObj parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CursorCommandBufObj(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CursorCommandBufObj> getParserForType() {
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

    // required sint32 movement = 2;
    public static final int MOVEMENT_FIELD_NUMBER = 2;
    private int movement_;
    /**
     * <code>required sint32 movement = 2;</code>
     */
    public boolean hasMovement() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required sint32 movement = 2;</code>
     */
    public int getMovement() {
      return movement_;
    }

    private void initFields() {
      clientID_ = 0;
      movement_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasClientID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMovement()) {
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
        output.writeSInt32(2, movement_);
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
          .computeSInt32Size(2, movement_);
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

    public static CursorCommandBuf.CursorCommandBufObj parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static CursorCommandBuf.CursorCommandBufObj parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(CursorCommandBuf.CursorCommandBufObj prototype) {
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
     * Protobuf type {@code CursorCommandBufObj}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements CursorCommandBuf.CursorCommandBufObjOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return CursorCommandBuf.internal_static_CursorCommandBufObj_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return CursorCommandBuf.internal_static_CursorCommandBufObj_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                CursorCommandBuf.CursorCommandBufObj.class, CursorCommandBuf.CursorCommandBufObj.Builder.class);
      }

      // Construct using CursorCommandBuf.CursorCommandBufObj.newBuilder()
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
        movement_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return CursorCommandBuf.internal_static_CursorCommandBufObj_descriptor;
      }

      public CursorCommandBuf.CursorCommandBufObj getDefaultInstanceForType() {
        return CursorCommandBuf.CursorCommandBufObj.getDefaultInstance();
      }

      public CursorCommandBuf.CursorCommandBufObj build() {
        CursorCommandBuf.CursorCommandBufObj result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public CursorCommandBuf.CursorCommandBufObj buildPartial() {
        CursorCommandBuf.CursorCommandBufObj result = new CursorCommandBuf.CursorCommandBufObj(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.clientID_ = clientID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.movement_ = movement_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof CursorCommandBuf.CursorCommandBufObj) {
          return mergeFrom((CursorCommandBuf.CursorCommandBufObj)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(CursorCommandBuf.CursorCommandBufObj other) {
        if (other == CursorCommandBuf.CursorCommandBufObj.getDefaultInstance()) return this;
        if (other.hasClientID()) {
          setClientID(other.getClientID());
        }
        if (other.hasMovement()) {
          setMovement(other.getMovement());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasClientID()) {
          
          return false;
        }
        if (!hasMovement()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        CursorCommandBuf.CursorCommandBufObj parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (CursorCommandBuf.CursorCommandBufObj) e.getUnfinishedMessage();
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

      // required sint32 movement = 2;
      private int movement_ ;
      /**
       * <code>required sint32 movement = 2;</code>
       */
      public boolean hasMovement() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required sint32 movement = 2;</code>
       */
      public int getMovement() {
        return movement_;
      }
      /**
       * <code>required sint32 movement = 2;</code>
       */
      public Builder setMovement(int value) {
        bitField0_ |= 0x00000002;
        movement_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required sint32 movement = 2;</code>
       */
      public Builder clearMovement() {
        bitField0_ = (bitField0_ & ~0x00000002);
        movement_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:CursorCommandBufObj)
    }

    static {
      defaultInstance = new CursorCommandBufObj(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:CursorCommandBufObj)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_CursorCommandBufObj_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_CursorCommandBufObj_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030cursor_command_buf.proto\"9\n\023CursorComm" +
      "andBufObj\022\020\n\010clientID\030\001 \002(\005\022\020\n\010movement\030" +
      "\002 \002(\021"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_CursorCommandBufObj_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_CursorCommandBufObj_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_CursorCommandBufObj_descriptor,
              new java.lang.String[] { "ClientID", "Movement", });
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