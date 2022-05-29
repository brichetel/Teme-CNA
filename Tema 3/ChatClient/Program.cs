using Grpc.Core;
using Grpc.Net.Client;
using System;
using System.Threading.Tasks;

namespace ChatClient
{
    class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine("Insert your name: ");
            string name = Console.ReadLine();
            using var channel = GrpcChannel.ForAddress("https://localhost:5001");
            var client = new ChatService.ChatServiceClient(channel);
            using var call = client.chat();

            //Console.WriteLine("Starting background task to receive messages");
            var readTask = Task.Run(async () =>
            {
                await foreach (var response in call.ResponseStream.ReadAllAsync())
                    Console.WriteLine(response.Name + ": " + response.Message);
            });

            Console.WriteLine("Starting to send messages");
            Console.WriteLine("Write a message then press enter");
            Console.WriteLine("If you want to disconnect press enter");

            while (true)
            {
                var result = Console.ReadLine();
                if (!string.IsNullOrEmpty(result))
                {
                    await call.RequestStream.WriteAsync(new ChatRequest { Name = name, Message = result });
                }
                else
                {
                    break;
                }
            }

            Console.WriteLine("Disconnecting...");
            await call.RequestStream.WriteAsync(new ChatRequest { Status = true, Name = name });
            await call.RequestStream.CompleteAsync();
            await readTask;
        }

    }
}