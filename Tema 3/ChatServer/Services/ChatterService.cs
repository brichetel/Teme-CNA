using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Grpc.Core;
using Microsoft.Extensions.Logging;

namespace ChatServer.Services
{
	public class ChatterService : ChatService.ChatServiceBase
	{
		private readonly ILogger<ChatterService> _logger;
		static List<string> persons = new List<string>();
		static List<IServerStreamWriter<ChatReply>> responseStreams = new List<IServerStreamWriter<ChatReply>>();

		public ChatterService(ILogger<ChatterService> logger)
		{
			_logger = logger;
		}

        public override async Task chat(IAsyncStreamReader<ChatRequest> requestStream, IServerStreamWriter<ChatReply> responseStream,
			ServerCallContext context)
		{

			if (!responseStreams.Contains(responseStream))
			{
				responseStreams.Add(responseStream);
			}

			while (await requestStream.MoveNext(CancellationToken.None))
			{
				var statusfromClient = requestStream.Current;
				if (statusfromClient.Status == false)
				{
					if (!responseStreams.Contains(responseStream))
						responseStreams.Add(responseStream);
					if (!persons.Contains(requestStream.Current.Name))
					{
						persons.Add(requestStream.Current.Name);
						Console.WriteLine("");
						Console.WriteLine(requestStream.Current.Name + " join at " + DateTime.Now);

						_logger.LogInformation(requestStream.Current.Name + " join at " + DateTime.Now);
					}


					Console.WriteLine(DateTime.Now + "|"+ requestStream.Current.Name + "----->message: " + requestStream.Current.Message);
					Console.WriteLine("");
					_logger.LogInformation(DateTime.Now + "|" + requestStream.Current.Name + "----->message: " + requestStream.Current.Message+"\n");
					var messageFromClient = requestStream.Current;

					var message = new ChatReply
					{
						Name = messageFromClient.Name,
						Message = messageFromClient.Message
					};

					var toRemoveList = new List<IServerStreamWriter<ChatReply>>();
					foreach (var stream in responseStreams.ToList())
					{
						try
						{
							await stream.WriteAsync(message);
						}
						catch (Exception)
						{
							toRemoveList.Add(responseStream);
						}
					}

					responseStreams.RemoveAll(stream => toRemoveList.Contains(stream));
				}
				else
				{

					Console.WriteLine(requestStream.Current.Name + " disconnected at " + DateTime.Now);
				}
			}
		}
	}
}