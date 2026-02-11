using Microsoft.AspNetCore.Mvc;
using LoggingService.Data;
using LoggingService.Models;
using Microsoft.EntityFrameworkCore;

namespace LoggingService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LogsController : ControllerBase
    {
        private readonly AppDbContext _context;

        public LogsController(AppDbContext context)
        {
            _context = context;
        }

        // POST: api/logs
        [HttpPost]
        public async Task<ActionResult<LogEntry>> PostLog(LogEntry logEntry)
        {
            logEntry.Timestamp = DateTime.UtcNow; // Ensure server time
            _context.Logs.Add(logEntry);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetLogs), new { id = logEntry.Id }, logEntry);
        }

        // GET: api/logs
        [HttpGet]
        public async Task<ActionResult<IEnumerable<LogEntry>>> GetLogs([FromQuery] string? level, [FromQuery] string? userId)
        {
            var query = _context.Logs.AsQueryable();

            if (!string.IsNullOrEmpty(level))
            {
                query = query.Where(l => l.Level == level);
            }

            if (!string.IsNullOrEmpty(userId))
            {
                query = query.Where(l => l.UserId == userId);
            }

            return await query.OrderByDescending(l => l.Timestamp).Take(100).ToListAsync();
        }
    }
}
