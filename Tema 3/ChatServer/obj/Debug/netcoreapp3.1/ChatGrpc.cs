// <auto-generated>
//     Generated by the protocol buffer compiler.  DO NOT EDIT!
//     source: Protos/chat.proto
// </auto-generated>
#pragma warning disable 0414, 1591
#region Designer generated code

using grpc = global::Grpc.Core;

namespace ChatServer {
  public static partial class ChatService
  {
    static readonly string __ServiceName = "chatter.ChatService";

    static readonly grpc::Marshaller<global::ChatServer.ChatRequest> __Marshaller_chatter_ChatRequest = grpc::Marshallers.Create((arg) => global::Google.Protobuf.MessageExtensions.ToByteArray(arg), global::ChatServer.ChatRequest.Parser.ParseFrom);
    static readonly grpc::Marshaller<global::ChatServer.ChatReply> __Marshaller_chatter_ChatReply = grpc::Marshallers.Create((arg) => global::Google.Protobuf.MessageExtensions.ToByteArray(arg), global::ChatServer.ChatReply.Parser.ParseFrom);

    static readonly grpc::Method<global::ChatServer.ChatRequest, global::ChatServer.ChatReply> __Method_chat = new grpc::Method<global::ChatServer.ChatRequest, global::ChatServer.ChatReply>(
        grpc::MethodType.DuplexStreaming,
        __ServiceName,
        "chat",
        __Marshaller_chatter_ChatRequest,
        __Marshaller_chatter_ChatReply);

    /// <summary>Service descriptor</summary>
    public static global::Google.Protobuf.Reflection.ServiceDescriptor Descriptor
    {
      get { return global::ChatServer.ChatReflection.Descriptor.Services[0]; }
    }

    /// <summary>Base class for server-side implementations of ChatService</summary>
    [grpc::BindServiceMethod(typeof(ChatService), "BindService")]
    public abstract partial class ChatServiceBase
    {
      public virtual global::System.Threading.Tasks.Task chat(grpc::IAsyncStreamReader<global::ChatServer.ChatRequest> requestStream, grpc::IServerStreamWriter<global::ChatServer.ChatReply> responseStream, grpc::ServerCallContext context)
      {
        throw new grpc::RpcException(new grpc::Status(grpc::StatusCode.Unimplemented, ""));
      }

    }

    /// <summary>Creates service definition that can be registered with a server</summary>
    /// <param name="serviceImpl">An object implementing the server-side handling logic.</param>
    public static grpc::ServerServiceDefinition BindService(ChatServiceBase serviceImpl)
    {
      return grpc::ServerServiceDefinition.CreateBuilder()
          .AddMethod(__Method_chat, serviceImpl.chat).Build();
    }

    /// <summary>Register service method with a service binder with or without implementation. Useful when customizing the  service binding logic.
    /// Note: this method is part of an experimental API that can change or be removed without any prior notice.</summary>
    /// <param name="serviceBinder">Service methods will be bound by calling <c>AddMethod</c> on this object.</param>
    /// <param name="serviceImpl">An object implementing the server-side handling logic.</param>
    public static void BindService(grpc::ServiceBinderBase serviceBinder, ChatServiceBase serviceImpl)
    {
      serviceBinder.AddMethod(__Method_chat, serviceImpl == null ? null : new grpc::DuplexStreamingServerMethod<global::ChatServer.ChatRequest, global::ChatServer.ChatReply>(serviceImpl.chat));
    }

  }
}
#endregion
