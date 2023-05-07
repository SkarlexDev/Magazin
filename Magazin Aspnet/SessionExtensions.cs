using System.Runtime.Serialization.Formatters.Binary;

namespace Magazin
{
    public static class SessionExtensions
    {
        public static void SetObject<T>(this ISession session, string key, T value)
        {
            using (var ms = new MemoryStream())
            {
                var formatter = new BinaryFormatter();
                formatter.Serialize(ms, value);
                session.Set(key, ms.ToArray());
            }
        }

        public static T GetObject<T>(this ISession session, string key)
        {
            byte[] value;
            if (session.TryGetValue(key, out value))
            {
                using (var ms = new MemoryStream(value))
                {
                    var formatter = new BinaryFormatter();
                    return (T)formatter.Deserialize(ms);
                }
            }
            else
            {
                return default(T);
            }
        }
    }
}
